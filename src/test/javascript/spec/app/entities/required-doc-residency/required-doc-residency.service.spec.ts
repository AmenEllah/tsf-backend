import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RequiredDocResidencyService } from 'app/entities/required-doc-residency/required-doc-residency.service';
import { IRequiredDocResidency, RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

describe('Service Tests', () => {
  describe('RequiredDocResidency Service', () => {
    let injector: TestBed;
    let service: RequiredDocResidencyService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequiredDocResidency;
    let expectedResult: IRequiredDocResidency | IRequiredDocResidency[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequiredDocResidencyService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new RequiredDocResidency(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, false, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            deliveryDate: currentDate.format(DATE_FORMAT),
            experationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RequiredDocResidency', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            deliveryDate: currentDate.format(DATE_FORMAT),
            experationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
            experationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new RequiredDocResidency()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RequiredDocResidency', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            num: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_FORMAT),
            experationDate: currentDate.format(DATE_FORMAT),
            illimitedExperationDate: true,
            residencyRecto: 'BBBBBB',
            residencyVerso: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
            experationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RequiredDocResidency', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            num: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_FORMAT),
            experationDate: currentDate.format(DATE_FORMAT),
            illimitedExperationDate: true,
            residencyRecto: 'BBBBBB',
            residencyVerso: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
            experationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RequiredDocResidency', () => {
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
