import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AccomodationService } from 'app/entities/accomodation/accomodation.service';
import { IAccomodation, Accomodation } from 'app/shared/model/accomodation.model';
import { AccomodationType } from 'app/shared/model/enumerations/accomodation-type.model';
import { AccomodationClass } from 'app/shared/model/enumerations/accomodation-class.model';

describe('Service Tests', () => {
  describe('Accomodation Service', () => {
    let injector: TestBed;
    let service: AccomodationService;
    let httpMock: HttpTestingController;
    let elemDefault: IAccomodation;
    let expectedResult: IAccomodation | IAccomodation[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AccomodationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Accomodation(0, 'AAAAAAA', AccomodationType.HOTEL, AccomodationClass.FIRST);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Accomodation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Accomodation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Accomodation', () => {
        const returnedFromService = Object.assign(
          {
            accomodationName: 'BBBBBB',
            accomodationType: 'BBBBBB',
            accomodationClass: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Accomodation', () => {
        const returnedFromService = Object.assign(
          {
            accomodationName: 'BBBBBB',
            accomodationType: 'BBBBBB',
            accomodationClass: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Accomodation', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
