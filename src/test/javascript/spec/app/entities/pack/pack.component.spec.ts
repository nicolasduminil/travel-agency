import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TravelAgencyTestModule } from '../../../test.module';
import { PackComponent } from 'app/entities/pack/pack.component';
import { PackService } from 'app/entities/pack/pack.service';
import { Pack } from 'app/shared/model/pack.model';

describe('Component Tests', () => {
  describe('Pack Management Component', () => {
    let comp: PackComponent;
    let fixture: ComponentFixture<PackComponent>;
    let service: PackService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [PackComponent],
      })
        .overrideTemplate(PackComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PackComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PackService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Pack(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.packs && comp.packs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
