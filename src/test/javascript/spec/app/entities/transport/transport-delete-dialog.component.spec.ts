import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TravelAgencyTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TransportDeleteDialogComponent } from 'app/entities/transport/transport-delete-dialog.component';
import { TransportService } from 'app/entities/transport/transport.service';

describe('Component Tests', () => {
  describe('Transport Management Delete Component', () => {
    let comp: TransportDeleteDialogComponent;
    let fixture: ComponentFixture<TransportDeleteDialogComponent>;
    let service: TransportService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [TransportDeleteDialogComponent],
      })
        .overrideTemplate(TransportDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransportDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransportService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
