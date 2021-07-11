import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { BotScanDetailComponent } from 'app/entities/bot-scan/bot-scan-detail.component';
import { BotScan } from 'app/shared/model/bot-scan.model';

describe('Component Tests', () => {
  describe('BotScan Management Detail Component', () => {
    let comp: BotScanDetailComponent;
    let fixture: ComponentFixture<BotScanDetailComponent>;
    const route = ({ data: of({ botScan: new BotScan(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [BotScanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BotScanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BotScanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load botScan on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.botScan).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
