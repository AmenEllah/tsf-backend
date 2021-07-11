import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PersonalInfoService } from 'app/entities/personal-info/personal-info.service';
import { IPersonalInfo, PersonalInfo } from 'app/shared/model/personal-info.model';
import { Civility } from 'app/shared/model/enumerations/civility.model';

describe('Service Tests', () => {
  describe('PersonalInfo Service', () => {
    let injector: TestBed;
    let service: PersonalInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IPersonalInfo;
    let expectedResult: IPersonalInfo | IPersonalInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PersonalInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PersonalInfo(
        0,
        Civility.MADAME,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            birthday: currentDate.format(DATE_FORMAT),
            cinDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyExpirationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PersonalInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            birthday: currentDate.format(DATE_FORMAT),
            cinDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyExpirationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthday: currentDate,
            cinDeliveryDate: currentDate,
            abroadResidancyDeliveryDate: currentDate,
            abroadResidancyExpirationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new PersonalInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PersonalInfo', () => {
        const returnedFromService = Object.assign(
          {
            civility: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            nativeCountry: 'BBBBBB',
            birthday: currentDate.format(DATE_FORMAT),
            clientABT: true,
            rib: 'BBBBBB',
            nationality: 'BBBBBB',
            secondNationality: 'BBBBBB',
            nbrkids: 1,
            maritalStatus: 'BBBBBB',
            phone: 'BBBBBB',
            americanIndex: true,
            accountNumber: 'BBBBBB',
            cin: 'BBBBBB',
            abroadResidancyProof: 'BBBBBB',
            abroadResidancyNumber: 'BBBBBB',
            cinDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyExpirationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthday: currentDate,
            cinDeliveryDate: currentDate,
            abroadResidancyDeliveryDate: currentDate,
            abroadResidancyExpirationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PersonalInfo', () => {
        const returnedFromService = Object.assign(
          {
            civility: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            nativeCountry: 'BBBBBB',
            birthday: currentDate.format(DATE_FORMAT),
            clientABT: true,
            rib: 'BBBBBB',
            nationality: 'BBBBBB',
            secondNationality: 'BBBBBB',
            nbrkids: 1,
            maritalStatus: 'BBBBBB',
            phone: 'BBBBBB',
            americanIndex: true,
            accountNumber: 'BBBBBB',
            cin: 'BBBBBB',
            abroadResidancyProof: 'BBBBBB',
            abroadResidancyNumber: 'BBBBBB',
            cinDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyDeliveryDate: currentDate.format(DATE_TIME_FORMAT),
            abroadResidancyExpirationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthday: currentDate,
            cinDeliveryDate: currentDate,
            abroadResidancyDeliveryDate: currentDate,
            abroadResidancyExpirationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PersonalInfo', () => {
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
