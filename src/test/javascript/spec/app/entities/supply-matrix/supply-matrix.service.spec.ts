import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SupplyMatrixService } from 'app/entities/supply-matrix/supply-matrix.service';
import { ISupplyMatrix, SupplyMatrix } from 'app/shared/model/supply-matrix.model';

describe('Service Tests', () => {
  describe('SupplyMatrix Service', () => {
    let injector: TestBed;
    let service: SupplyMatrixService;
    let httpMock: HttpTestingController;
    let elemDefault: ISupplyMatrix;
    let expectedResult: ISupplyMatrix | ISupplyMatrix[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SupplyMatrixService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SupplyMatrix(0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SupplyMatrix', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SupplyMatrix()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SupplyMatrix', () => {
        const returnedFromService = Object.assign(
          {
            categoryId: 1,
            incomeTypeCode: 'BBBBBB',
            incomeType: 'BBBBBB',
            monthlyIncomeId: 1,
            marketCode: 1,
            market: 'BBBBBB',
            segmentCode: 'BBBBBB',
            segment: 'BBBBBB',
            activityId: 1,
            professionCode: 'BBBBBB',
            profession: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SupplyMatrix', () => {
        const returnedFromService = Object.assign(
          {
            categoryId: 1,
            incomeTypeCode: 'BBBBBB',
            incomeType: 'BBBBBB',
            monthlyIncomeId: 1,
            marketCode: 1,
            market: 'BBBBBB',
            segmentCode: 'BBBBBB',
            segment: 'BBBBBB',
            activityId: 1,
            professionCode: 'BBBBBB',
            profession: 'BBBBBB',
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

      it('should delete a SupplyMatrix', () => {
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
