import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { BotScanComponent } from './bot-scan.component';
import { BotScanDetailComponent } from './bot-scan-detail.component';
import { BotScanUpdateComponent } from './bot-scan-update.component';
import { BotScanDeleteDialogComponent } from './bot-scan-delete-dialog.component';
import { botScanRoute } from './bot-scan.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(botScanRoute)],
  declarations: [BotScanComponent, BotScanDetailComponent, BotScanUpdateComponent, BotScanDeleteDialogComponent],
  entryComponents: [BotScanDeleteDialogComponent],
})
export class TfsBackendBotScanModule {}
