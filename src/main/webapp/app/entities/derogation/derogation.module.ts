import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { DerogationComponent } from './derogation.component';
import { DerogationDetailComponent } from './derogation-detail.component';
import { DerogationUpdateComponent } from './derogation-update.component';
import { DerogationDeleteDialogComponent } from './derogation-delete-dialog.component';
import { derogationRoute } from './derogation.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(derogationRoute)],
  declarations: [DerogationComponent, DerogationDetailComponent, DerogationUpdateComponent, DerogationDeleteDialogComponent],
  entryComponents: [DerogationDeleteDialogComponent],
})
export class TfsBackendDerogationModule {}
