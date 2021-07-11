import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { BotScanUpdateComponent } from 'app/entities/bot-scan/bot-scan-update.component';
import { BotScanService } from 'app/entities/bot-scan/bot-scan.service';
import { BotScan } from 'app/shared/model/bot-scan.model';

describe('Component Tests', () => {
  describe('BotScan Management Update Component', () => {
    let comp: BotScanUpdateComponent;
    let fixture: ComponentFixture<BotScanUpdateComponent>;
    let service: BotScanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [BotScanUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BotScanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BotScanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BotScanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BotScan(123);
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
        const entity = new BotScan();
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
