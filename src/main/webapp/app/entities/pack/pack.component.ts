import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPack } from 'app/shared/model/pack.model';
import { PackService } from './pack.service';
import { PackDeleteDialogComponent } from './pack-delete-dialog.component';

@Component({
  selector: 'jhi-pack',
  templateUrl: './pack.component.html',
})
export class PackComponent implements OnInit, OnDestroy {
  packs?: IPack[];
  eventSubscriber?: Subscription;

  constructor(protected packService: PackService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.packService.query().subscribe((res: HttpResponse<IPack[]>) => (this.packs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPacks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPack): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPacks(): void {
    this.eventSubscriber = this.eventManager.subscribe('packListModification', () => this.loadAll());
  }

  delete(pack: IPack): void {
    const modalRef = this.modalService.open(PackDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pack = pack;
  }
}
