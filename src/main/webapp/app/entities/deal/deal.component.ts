import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';
import { DealDeleteDialogComponent } from './deal-delete-dialog.component';

@Component({
  selector: 'jhi-deal',
  templateUrl: './deal.component.html',
})
export class DealComponent implements OnInit, OnDestroy {
  deals?: IDeal[];
  eventSubscriber?: Subscription;

  constructor(protected dealService: DealService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDeals();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDeal): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDeals(): void {
    this.eventSubscriber = this.eventManager.subscribe('dealListModification', () => this.loadAll());
  }

  delete(deal: IDeal): void {
    const modalRef = this.modalService.open(DealDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.deal = deal;
  }
}
