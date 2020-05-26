import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAccomodation } from 'app/shared/model/accomodation.model';
import { AccomodationService } from './accomodation.service';
import { AccomodationDeleteDialogComponent } from './accomodation-delete-dialog.component';

@Component({
  selector: 'jhi-accomodation',
  templateUrl: './accomodation.component.html',
})
export class AccomodationComponent implements OnInit, OnDestroy {
  accomodations?: IAccomodation[];
  eventSubscriber?: Subscription;

  constructor(
    protected accomodationService: AccomodationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.accomodationService.query().subscribe((res: HttpResponse<IAccomodation[]>) => (this.accomodations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAccomodations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAccomodation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAccomodations(): void {
    this.eventSubscriber = this.eventManager.subscribe('accomodationListModification', () => this.loadAll());
  }

  delete(accomodation: IAccomodation): void {
    const modalRef = this.modalService.open(AccomodationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.accomodation = accomodation;
  }
}
