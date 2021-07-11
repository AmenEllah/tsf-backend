import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { GovernorateComponent } from './governorate.component';
import { GovernorateDetailComponent } from './governorate-detail.component';
import { GovernorateUpdateComponent } from './governorate-update.component';
import { GovernorateDeleteDialogComponent } from './governorate-delete-dialog.component';
import { governorateRoute } from './governorate.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(governorateRoute)],
  declarations: [GovernorateComponent, GovernorateDetailComponent, GovernorateUpdateComponent, GovernorateDeleteDialogComponent],
  entryComponents: [GovernorateDeleteDialogComponent],
})
export class TfsBackendGovernorateModule {}
