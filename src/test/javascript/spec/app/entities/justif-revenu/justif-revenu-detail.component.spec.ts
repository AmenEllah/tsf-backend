import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { JustifRevenuDetailComponent } from 'app/entities/justif-revenu/justif-revenu-detail.component';
import { JustifRevenu } from 'app/shared/model/justif-revenu.model';

describe('Component Tests', () => {
  describe('JustifRevenu Management Detail Component', () => {
    let comp: JustifRevenuDetailComponent;
    let fixture: ComponentFixture<JustifRevenuDetailComponent>;
    const route = ({ data: of({ justifRevenu: new JustifRevenu(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [JustifRevenuDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(JustifRevenuDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JustifRevenuDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load justifRevenu on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.justifRevenu).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
