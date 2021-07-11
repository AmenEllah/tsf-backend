import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SubcontractingStaffDetailComponent } from 'app/entities/subcontracting-staff/subcontracting-staff-detail.component';
import { SubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';

describe('Component Tests', () => {
  describe('SubcontractingStaff Management Detail Component', () => {
    let comp: SubcontractingStaffDetailComponent;
    let fixture: ComponentFixture<SubcontractingStaffDetailComponent>;
    const route = ({ data: of({ subcontractingStaff: new SubcontractingStaff(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SubcontractingStaffDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SubcontractingStaffDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubcontractingStaffDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subcontractingStaff on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subcontractingStaff).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
