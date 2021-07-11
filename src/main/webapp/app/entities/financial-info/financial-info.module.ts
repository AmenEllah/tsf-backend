import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { FinancialInfoComponent } from './financial-info.component';
import { FinancialInfoDetailComponent } from './financial-info-detail.component';
import { FinancialInfoUpdateComponent } from './financial-info-update.component';
import { FinancialInfoDeleteDialogComponent } from './financial-info-delete-dialog.component';
import { financialInfoRoute } from './financial-info.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(financialInfoRoute)],
  declarations: [FinancialInfoComponent, FinancialInfoDetailComponent, FinancialInfoUpdateComponent, FinancialInfoDeleteDialogComponent],
  entryComponents: [FinancialInfoDeleteDialogComponent],
})
export class TfsBackendFinancialInfoModule {}
