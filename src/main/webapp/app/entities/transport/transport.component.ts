import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITransport } from 'app/shared/model/transport.model';
import { TransportService } from './transport.service';
import { TransportDeleteDialogComponent } from './transport-delete-dialog.component';

@Component({
  selector: 'jhi-transport',
  templateUrl: './transport.component.html',
})
export class TransportComponent implements OnInit, OnDestroy {
  transports?: ITransport[];
  eventSubscriber?: Subscription;

  constructor(protected transportService: TransportService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.transportService.query().subscribe((res: HttpResponse<ITransport[]>) => (this.transports = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTransports();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITransport): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTransports(): void {
    this.eventSubscriber = this.eventManager.subscribe('transportListModification', () => this.loadAll());
  }

  delete(transport: ITransport): void {
    const modalRef = this.modalService.open(TransportDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.transport = transport;
  }
}
