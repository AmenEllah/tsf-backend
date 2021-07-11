import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { NationalityComponent } from './nationality.component';
import { NationalityDetailComponent } from './nationality-detail.component';
import { NationalityUpdateComponent } from './nationality-update.component';
import { NationalityDeleteDialogComponent } from './nationality-delete-dialog.component';
import { nationalityRoute } from './nationality.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(nationalityRoute)],
  declarations: [NationalityComponent, NationalityDetailComponent, NationalityUpdateComponent, NationalityDeleteDialogComponent],
  entryComponents: [NationalityDeleteDialogComponent],
})
export class TfsBackendNationalityModule {}
