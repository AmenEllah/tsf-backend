import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { JustifRevenuComponent } from './justif-revenu.component';
import { JustifRevenuDetailComponent } from './justif-revenu-detail.component';
import { JustifRevenuUpdateComponent } from './justif-revenu-update.component';
import { JustifRevenuDeleteDialogComponent } from './justif-revenu-delete-dialog.component';
import { justifRevenuRoute } from './justif-revenu.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(justifRevenuRoute)],
  declarations: [JustifRevenuComponent, JustifRevenuDetailComponent, JustifRevenuUpdateComponent, JustifRevenuDeleteDialogComponent],
  entryComponents: [JustifRevenuDeleteDialogComponent],
})
export class TfsBackendJustifRevenuModule {}
