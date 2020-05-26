import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IContact, Contact } from 'app/shared/model/contact.model';
import { ContactService } from './contact.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from 'app/entities/activity/activity.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

type SelectableEntity = ILocation | IActivity | ICustomer | IDeal;

@Component({
  selector: 'jhi-contact-update',
  templateUrl: './contact-update.component.html',
})
export class ContactUpdateComponent implements OnInit {
  isSaving = false;
  addresses: ILocation[] = [];
  activities: IActivity[] = [];
  customers: ICustomer[] = [];
  deals: IDeal[] = [];

  editForm = this.fb.group({
    id: [],
    contactName: [null, [Validators.required]],
    contactFirstName: [null, [Validators.required]],
    contactLastName: [null, [Validators.required]],
    contactEmailAddress: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    contactWebSite: [null, [Validators.required]],
    contactSalutation: [null, [Validators.required]],
    contactJobTitle: [null, [Validators.required]],
    contactPhoneNumber: [null, [Validators.required]],
    contactFaxNumber: [],
    addressId: [],
    activityId: [],
    customerId: [],
    dealId: [],
  });

  constructor(
    protected contactService: ContactService,
    protected locationService: LocationService,
    protected activityService: ActivityService,
    protected customerService: CustomerService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contact }) => {
      this.updateForm(contact);

      this.locationService
        .query({ filter: 'contact-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!contact.addressId) {
            this.addresses = resBody;
          } else {
            this.locationService
              .find(contact.addressId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.addresses = concatRes));
          }
        });

      this.activityService.query().subscribe((res: HttpResponse<IActivity[]>) => (this.activities = res.body || []));

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(contact: IContact): void {
    this.editForm.patchValue({
      id: contact.id,
      contactName: contact.contactName,
      contactFirstName: contact.contactFirstName,
      contactLastName: contact.contactLastName,
      contactEmailAddress: contact.contactEmailAddress,
      contactWebSite: contact.contactWebSite,
      contactSalutation: contact.contactSalutation,
      contactJobTitle: contact.contactJobTitle,
      contactPhoneNumber: contact.contactPhoneNumber,
      contactFaxNumber: contact.contactFaxNumber,
      addressId: contact.addressId,
      activityId: contact.activityId,
      customerId: contact.customerId,
      dealId: contact.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contact = this.createFromForm();
    if (contact.id !== undefined) {
      this.subscribeToSaveResponse(this.contactService.update(contact));
    } else {
      this.subscribeToSaveResponse(this.contactService.create(contact));
    }
  }

  private createFromForm(): IContact {
    return {
      ...new Contact(),
      id: this.editForm.get(['id'])!.value,
      contactName: this.editForm.get(['contactName'])!.value,
      contactFirstName: this.editForm.get(['contactFirstName'])!.value,
      contactLastName: this.editForm.get(['contactLastName'])!.value,
      contactEmailAddress: this.editForm.get(['contactEmailAddress'])!.value,
      contactWebSite: this.editForm.get(['contactWebSite'])!.value,
      contactSalutation: this.editForm.get(['contactSalutation'])!.value,
      contactJobTitle: this.editForm.get(['contactJobTitle'])!.value,
      contactPhoneNumber: this.editForm.get(['contactPhoneNumber'])!.value,
      contactFaxNumber: this.editForm.get(['contactFaxNumber'])!.value,
      addressId: this.editForm.get(['addressId'])!.value,
      activityId: this.editForm.get(['activityId'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContact>>): void {
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
}
