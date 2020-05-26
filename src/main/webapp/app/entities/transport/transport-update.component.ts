import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITransport, Transport } from 'app/shared/model/transport.model';
import { TransportService } from './transport.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { IService } from 'app/shared/model/service.model';
import { ServiceService } from 'app/entities/service/service.service';

type SelectableEntity = ILocation | IService;

@Component({
  selector: 'jhi-transport-update',
  templateUrl: './transport-update.component.html',
})
export class TransportUpdateComponent implements OnInit {
  isSaving = false;
  tos: ILocation[] = [];
  froms: ILocation[] = [];
  services: IService[] = [];

  editForm = this.fb.group({
    id: [],
    transportType: [null, [Validators.required]],
    transportName: [null, [Validators.required]],
    transportDescription: [null, [Validators.required]],
    toId: [],
    fromId: [],
    services: [],
  });

  constructor(
    protected transportService: TransportService,
    protected locationService: LocationService,
    protected serviceService: ServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transport }) => {
      this.updateForm(transport);

      this.locationService
        .query({ filter: 'transportto-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!transport.toId) {
            this.tos = resBody;
          } else {
            this.locationService
              .find(transport.toId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.tos = concatRes));
          }
        });

      this.locationService
        .query({ filter: 'transportfrom-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!transport.fromId) {
            this.froms = resBody;
          } else {
            this.locationService
              .find(transport.fromId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.froms = concatRes));
          }
        });

      this.serviceService.query().subscribe((res: HttpResponse<IService[]>) => (this.services = res.body || []));
    });
  }

  updateForm(transport: ITransport): void {
    this.editForm.patchValue({
      id: transport.id,
      transportType: transport.transportType,
      transportName: transport.transportName,
      transportDescription: transport.transportDescription,
      toId: transport.toId,
      fromId: transport.fromId,
      services: transport.services,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transport = this.createFromForm();
    if (transport.id !== undefined) {
      this.subscribeToSaveResponse(this.transportService.update(transport));
    } else {
      this.subscribeToSaveResponse(this.transportService.create(transport));
    }
  }

  private createFromForm(): ITransport {
    return {
      ...new Transport(),
      id: this.editForm.get(['id'])!.value,
      transportType: this.editForm.get(['transportType'])!.value,
      transportName: this.editForm.get(['transportName'])!.value,
      transportDescription: this.editForm.get(['transportDescription'])!.value,
      toId: this.editForm.get(['toId'])!.value,
      fromId: this.editForm.get(['fromId'])!.value,
      services: this.editForm.get(['services'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransport>>): void {
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

  getSelected(selectedVals: IService[], option: IService): IService {
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
