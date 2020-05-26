import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TravelAgencyTestModule } from '../../../test.module';
import { AccomodationUpdateComponent } from 'app/entities/accomodation/accomodation-update.component';
import { AccomodationService } from 'app/entities/accomodation/accomodation.service';
import { Accomodation } from 'app/shared/model/accomodation.model';

describe('Component Tests', () => {
  describe('Accomodation Management Update Component', () => {
    let comp: AccomodationUpdateComponent;
    let fixture: ComponentFixture<AccomodationUpdateComponent>;
    let service: AccomodationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TravelAgencyTestModule],
        declarations: [AccomodationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AccomodationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AccomodationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AccomodationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Accomodation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Accomodation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
