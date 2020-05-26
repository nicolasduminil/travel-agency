import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html',
})
export class CustomerUpdateComponent implements OnInit {
  isSaving = false;
  deals: IDeal[] = [];
  customerBirthDateDp: any;

  editForm = this.fb.group({
    id: [],
    customerName: [null, [Validators.required]],
    customerGender: [],
    customerBirthDate: [],
    dealId: [],
  });

  constructor(
    protected customerService: CustomerService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(customer: ICustomer): void {
    this.editForm.patchValue({
      id: customer.id,
      customerName: customer.customerName,
      customerGender: customer.customerGender,
      customerBirthDate: customer.customerBirthDate,
      dealId: customer.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      customerGender: this.editForm.get(['customerGender'])!.value,
      customerBirthDate: this.editForm.get(['customerBirthDate'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>): void {
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

  trackById(index: number, item: IDeal): any {
    return item.id;
  }
}
