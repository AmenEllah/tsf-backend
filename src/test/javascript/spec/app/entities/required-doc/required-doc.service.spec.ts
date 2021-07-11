import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { IRequiredDoc, RequiredDoc } from 'app/shared/model/required-doc.model';

describe('Service Tests', () => {
  describe('RequiredDoc Service', () => {
    let injector: TestBed;
    let service: RequiredDocService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequiredDoc;
    let expectedResult: IRequiredDoc | IRequiredDoc[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequiredDocService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new RequiredDoc(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            deliveryDateCin: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RequiredDoc', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            deliveryDateCin: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDateCin: currentDate,
          },
          returnedFromService
        );

        service.create(new RequiredDoc()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RequiredDoc', () => {
        const returnedFromService = Object.assign(
          {
            label: 'BBBBBB',
            type: 'BBBBBB',
            numCIN: 'BBBBBB',
            deliveryDateCin: currentDate.format(DATE_FORMAT),
            rectoCin: 'BBBBBB',
            versoCin: 'BBBBBB',
            fatca: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDateCin: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RequiredDoc', () => {
        const returnedFromService = Object.assign(
          {
            label: 'BBBBBB',
            type: 'BBBBBB',
            numCIN: 'BBBBBB',
            deliveryDateCin: currentDate.format(DATE_FORMAT),
            rectoCin: 'BBBBBB',
            versoCin: 'BBBBBB',
            fatca: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDateCin: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RequiredDoc', () => {
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
