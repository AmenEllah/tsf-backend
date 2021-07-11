import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { JustifRevenuUpdateComponent } from 'app/entities/justif-revenu/justif-revenu-update.component';
import { JustifRevenuService } from 'app/entities/justif-revenu/justif-revenu.service';
import { JustifRevenu } from 'app/shared/model/justif-revenu.model';

describe('Component Tests', () => {
  describe('JustifRevenu Management Update Component', () => {
    let comp: JustifRevenuUpdateComponent;
    let fixture: ComponentFixture<JustifRevenuUpdateComponent>;
    let service: JustifRevenuService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [JustifRevenuUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(JustifRevenuUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JustifRevenuUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(JustifRevenuService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new JustifRevenu(123);
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
        const entity = new JustifRevenu();
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
