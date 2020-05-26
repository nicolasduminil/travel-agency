import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDeal, Deal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';

@Component({
  selector: 'jhi-deal-update',
  templateUrl: './deal-update.component.html',
})
export class DealUpdateComponent implements OnInit {
  isSaving = false;
  dealBookDateDp: any;

  editForm = this.fb.group({
    id: [],
    dealName: [null, [Validators.required]],
    dealBookDate: [null, [Validators.required]],
  });

  constructor(protected dealService: DealService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deal }) => {
      this.updateForm(deal);
    });
  }

  updateForm(deal: IDeal): void {
    this.editForm.patchValue({
      id: deal.id,
      dealName: deal.dealName,
      dealBookDate: deal.dealBookDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deal = this.createFromForm();
    if (deal.id !== undefined) {
      this.subscribeToSaveResponse(this.dealService.update(deal));
    } else {
      this.subscribeToSaveResponse(this.dealService.create(deal));
    }
  }

  private createFromForm(): IDeal {
    return {
      ...new Deal(),
      id: this.editForm.get(['id'])!.value,
      dealName: this.editForm.get(['dealName'])!.value,
      dealBookDate: this.editForm.get(['dealBookDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeal>>): void {
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
}
