import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IService, Service } from 'app/shared/model/service.model';
import { ServiceService } from './service.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';
import { IPack } from 'app/shared/model/pack.model';
import { PackService } from 'app/entities/pack/pack.service';
import { IAccomodation } from 'app/shared/model/accomodation.model';
import { AccomodationService } from 'app/entities/accomodation/accomodation.service';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from 'app/entities/activity/activity.service';

type SelectableEntity = IDeal | IPack | IAccomodation | IActivity;

@Component({
  selector: 'jhi-service-update',
  templateUrl: './service-update.component.html',
})
export class ServiceUpdateComponent implements OnInit {
  isSaving = false;
  deals: IDeal[] = [];
  packs: IPack[] = [];
  accomodations: IAccomodation[] = [];
  activities: IActivity[] = [];
  serviceStartDateDp: any;
  serviceEndDateDp: any;

  editForm = this.fb.group({
    id: [],
    serviceDescription: [null, [Validators.required]],
    serviceStartDate: [null, [Validators.required]],
    serviceEndDate: [null, [Validators.required]],
    servicePrice: [null, [Validators.required]],
    deals: [],
    packages: [],
    accomodations: [],
    activities: [],
  });

  constructor(
    protected serviceService: ServiceService,
    protected dealService: DealService,
    protected packService: PackService,
    protected accomodationService: AccomodationService,
    protected activityService: ActivityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ service }) => {
      this.updateForm(service);

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));

      this.packService.query().subscribe((res: HttpResponse<IPack[]>) => (this.packs = res.body || []));

      this.accomodationService.query().subscribe((res: HttpResponse<IAccomodation[]>) => (this.accomodations = res.body || []));

      this.activityService.query().subscribe((res: HttpResponse<IActivity[]>) => (this.activities = res.body || []));
    });
  }

  updateForm(service: IService): void {
    this.editForm.patchValue({
      id: service.id,
      serviceDescription: service.serviceDescription,
      serviceStartDate: service.serviceStartDate,
      serviceEndDate: service.serviceEndDate,
      servicePrice: service.servicePrice,
      deals: service.deals,
      packages: service.packages,
      accomodations: service.accomodations,
      activities: service.activities,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const service = this.createFromForm();
    if (service.id !== undefined) {
      this.subscribeToSaveResponse(this.serviceService.update(service));
    } else {
      this.subscribeToSaveResponse(this.serviceService.create(service));
    }
  }

  private createFromForm(): IService {
    return {
      ...new Service(),
      id: this.editForm.get(['id'])!.value,
      serviceDescription: this.editForm.get(['serviceDescription'])!.value,
      serviceStartDate: this.editForm.get(['serviceStartDate'])!.value,
      serviceEndDate: this.editForm.get(['serviceEndDate'])!.value,
      servicePrice: this.editForm.get(['servicePrice'])!.value,
      deals: this.editForm.get(['deals'])!.value,
      packages: this.editForm.get(['packages'])!.value,
      accomodations: this.editForm.get(['accomodations'])!.value,
      activities: this.editForm.get(['activities'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IService>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
