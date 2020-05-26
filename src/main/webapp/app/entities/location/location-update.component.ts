import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILocation, Location } from 'app/shared/model/location.model';
import { LocationService } from './location.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-location-update',
  templateUrl: './location-update.component.html',
})
export class LocationUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    streetAddress: [null, [Validators.required]],
    streetNumber: [null, [Validators.required]],
    postalCode: [null, [Validators.required]],
    city: [null, [Validators.required]],
    stateProvince: [null, [Validators.required]],
    countryName: [null, [Validators.required]],
    customerId: [],
  });

  constructor(
    protected locationService: LocationService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ location }) => {
      this.updateForm(location);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(location: ILocation): void {
    this.editForm.patchValue({
      id: location.id,
      streetAddress: location.streetAddress,
      streetNumber: location.streetNumber,
      postalCode: location.postalCode,
      city: location.city,
      stateProvince: location.stateProvince,
      countryName: location.countryName,
      customerId: location.customerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const location = this.createFromForm();
    if (location.id !== undefined) {
      this.subscribeToSaveResponse(this.locationService.update(location));
    } else {
      this.subscribeToSaveResponse(this.locationService.create(location));
    }
  }

  private createFromForm(): ILocation {
    return {
      ...new Location(),
      id: this.editForm.get(['id'])!.value,
      streetAddress: this.editForm.get(['streetAddress'])!.value,
      streetNumber: this.editForm.get(['streetNumber'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      city: this.editForm.get(['city'])!.value,
      stateProvince: this.editForm.get(['stateProvince'])!.value,
      countryName: this.editForm.get(['countryName'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocation>>): void {
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

  trackById(index: number, item: ICustomer): any {
    return item.id;
  }
}
