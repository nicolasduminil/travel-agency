import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccomodation } from 'app/shared/model/accomodation.model';
import { AccomodationService } from './accomodation.service';

@Component({
  templateUrl: './accomodation-delete-dialog.component.html',
})
export class AccomodationDeleteDialogComponent {
  accomodation?: IAccomodation;

  constructor(
    protected accomodationService: AccomodationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.accomodationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('accomodationListModification');
      this.activeModal.close();
    });
  }
}
