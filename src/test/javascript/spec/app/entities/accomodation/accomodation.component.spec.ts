import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TravelAgencyTestModule } from '../../../test.module';
import { AccomodationComponent } from 'app/entities/accomodation/accomodation.component';
import { AccomodationService } from 'app/entities/accomodation/accomodation.service';
import { Accomodation } from 'app/shared/model/accomodation.model';

describe('Component Tests', () => {
  describe('Accomodation Management Component', () => {
    let comp: AccomodationComponent;
    let fixture: ComponentFixture<AccomodationComponent>;
    let service: AccomodationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [AccomodationComponent],
      })
        .overrideTemplate(AccomodationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AccomodationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AccomodationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Accomodation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.accomodations && comp.accomodations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
