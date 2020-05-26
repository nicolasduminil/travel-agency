import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TravelAgencyTestModule } from '../../../test.module';
import { DealComponent } from 'app/entities/deal/deal.component';
import { DealService } from 'app/entities/deal/deal.service';
import { Deal } from 'app/shared/model/deal.model';

describe('Component Tests', () => {
  describe('Deal Management Component', () => {
    let comp: DealComponent;
    let fixture: ComponentFixture<DealComponent>;
    let service: DealService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [DealComponent],
      })
        .overrideTemplate(DealComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Deal(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.deals && comp.deals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
