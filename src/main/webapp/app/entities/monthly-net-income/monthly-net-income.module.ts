import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { MonthlyNetIncomeComponent } from './monthly-net-income.component';
import { MonthlyNetIncomeDetailComponent } from './monthly-net-income-detail.component';
import { MonthlyNetIncomeUpdateComponent } from './monthly-net-income-update.component';
import { MonthlyNetIncomeDeleteDialogComponent } from './monthly-net-income-delete-dialog.component';
import { monthlyNetIncomeRoute } from './monthly-net-income.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(monthlyNetIncomeRoute)],
  declarations: [
    MonthlyNetIncomeComponent,
    MonthlyNetIncomeDetailComponent,
    MonthlyNetIncomeUpdateComponent,
    MonthlyNetIncomeDeleteDialogComponent,
  ],
  entryComponents: [MonthlyNetIncomeDeleteDialogComponent],
})
export class TfsBackendMonthlyNetIncomeModule {}
