import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TravelAgencyTestModule } from '../../../test.module';
import { AccomodationDetailComponent } from 'app/entities/accomodation/accomodation-detail.component';
import { Accomodation } from 'app/shared/model/accomodation.model';

describe('Component Tests', () => {
  describe('Accomodation Management Detail Component', () => {
    let comp: AccomodationDetailComponent;
    let fixture: ComponentFixture<AccomodationDetailComponent>;
    const route = ({ data: of({ accomodation: new Accomodation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [AccomodationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AccomodationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AccomodationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load accomodation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.accomodation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
