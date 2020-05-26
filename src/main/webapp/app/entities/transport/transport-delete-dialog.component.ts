import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransport } from 'app/shared/model/transport.model';
import { TransportService } from './transport.service';

@Component({
  templateUrl: './transport-delete-dialog.component.html',
})
export class TransportDeleteDialogComponent {
  transport?: ITransport;

  constructor(protected transportService: TransportService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('transportListModification');
      this.activeModal.close();
    });
  }
}
