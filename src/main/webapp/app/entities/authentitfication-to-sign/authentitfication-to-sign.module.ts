import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { AuthentitficationToSignComponent } from './authentitfication-to-sign.component';
import { AuthentitficationToSignDetailComponent } from './authentitfication-to-sign-detail.component';
import { AuthentitficationToSignUpdateComponent } from './authentitfication-to-sign-update.component';
import { AuthentitficationToSignDeleteDialogComponent } from './authentitfication-to-sign-delete-dialog.component';
import { authentitficationToSignRoute } from './authentitfication-to-sign.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(authentitficationToSignRoute)],
  declarations: [
    AuthentitficationToSignComponent,
    AuthentitficationToSignDetailComponent,
    AuthentitficationToSignUpdateComponent,
    AuthentitficationToSignDeleteDialogComponent,
  ],
  entryComponents: [AuthentitficationToSignDeleteDialogComponent],
})
export class TfsBackendAuthentitficationToSignModule {}
