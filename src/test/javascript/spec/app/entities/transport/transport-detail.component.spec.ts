import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TravelAgencyTestModule } from '../../../test.module';
import { TransportDetailComponent } from 'app/entities/transport/transport-detail.component';
import { Transport } from 'app/shared/model/transport.model';

describe('Component Tests', () => {
  describe('Transport Management Detail Component', () => {
    let comp: TransportDetailComponent;
    let fixture: ComponentFixture<TransportDetailComponent>;
    const route = ({ data: of({ transport: new Transport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [TransportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TransportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load transport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.transport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
