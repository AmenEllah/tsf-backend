import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { RequiredDocIncomeComponent } from './required-doc-income.component';
import { RequiredDocIncomeDetailComponent } from './required-doc-income-detail.component';
import { RequiredDocIncomeUpdateComponent } from './required-doc-income-update.component';
import { RequiredDocIncomeDeleteDialogComponent } from './required-doc-income-delete-dialog.component';
import { requiredDocIncomeRoute } from './required-doc-income.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(requiredDocIncomeRoute)],
  declarations: [
    RequiredDocIncomeComponent,
    RequiredDocIncomeDetailComponent,
    RequiredDocIncomeUpdateComponent,
    RequiredDocIncomeDeleteDialogComponent,
  ],
  entryComponents: [RequiredDocIncomeDeleteDialogComponent],
})
export class TfsBackendRequiredDocIncomeModule {}
