import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AuthentitficationToSignService } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign.service';
import { IAuthentitficationToSign, AuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';

describe('Service Tests', () => {
  describe('AuthentitficationToSign Service', () => {
    let injector: TestBed;
    let service: AuthentitficationToSignService;
    let httpMock: HttpTestingController;
    let elemDefault: IAuthentitficationToSign;
    let expectedResult: IAuthentitficationToSign | IAuthentitficationToSign[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AuthentitficationToSignService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AuthentitficationToSign(0, 'AAAAAAA', 'AAAAAAA', currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateCreation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AuthentitficationToSign', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateCreation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.create(new AuthentitficationToSign()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AuthentitficationToSign', () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            token: 'BBBBBB',
            dateCreation: currentDate.format(DATE_FORMAT),
            valide: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AuthentitficationToSign', () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            token: 'BBBBBB',
            dateCreation: currentDate.format(DATE_FORMAT),
            valide: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AuthentitficationToSign', () => {
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
