import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAccomodation, Accomodation } from 'app/shared/model/accomodation.model';
import { AccomodationService } from './accomodation.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

@Component({
  selector: 'jhi-accomodation-update',
  templateUrl: './accomodation-update.component.html',
})
export class AccomodationUpdateComponent implements OnInit {
  isSaving = false;
  locations: ILocation[] = [];

  editForm = this.fb.group({
    id: [],
    accomodationName: [null, [Validators.required]],
    accomodationType: [null, [Validators.required]],
    accomodationClass: [null, [Validators.required]],
    locationId: [],
  });

  constructor(
    protected accomodationService: AccomodationService,
    protected locationService: LocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ accomodation }) => {
      this.updateForm(accomodation);

      this.locationService
        .query({ filter: 'accomodation-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!accomodation.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(accomodation.locationId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.locations = concatRes));
          }
        });
    });
  }

  updateForm(accomodation: IAccomodation): void {
    this.editForm.patchValue({
      id: accomodation.id,
      accomodationName: accomodation.accomodationName,
      accomodationType: accomodation.accomodationType,
      accomodationClass: accomodation.accomodationClass,
      locationId: accomodation.locationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const accomodation = this.createFromForm();
    if (accomodation.id !== undefined) {
      this.subscribeToSaveResponse(this.accomodationService.update(accomodation));
    } else {
      this.subscribeToSaveResponse(this.accomodationService.create(accomodation));
    }
  }

  private createFromForm(): IAccomodation {
    return {
      ...new Accomodation(),
      id: this.editForm.get(['id'])!.value,
      accomodationName: this.editForm.get(['accomodationName'])!.value,
      accomodationType: this.editForm.get(['accomodationType'])!.value,
      accomodationClass: this.editForm.get(['accomodationClass'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAccomodation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILocation): any {
    return item.id;
  }
}
