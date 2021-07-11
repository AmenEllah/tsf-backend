import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { AppSettingsComponent } from './app-settings.component';
import { AppSettingsDetailComponent } from './app-settings-detail.component';
import { AppSettingsUpdateComponent } from './app-settings-update.component';
import { AppSettingsDeleteDialogComponent } from './app-settings-delete-dialog.component';
import { appSettingsRoute } from './app-settings.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(appSettingsRoute)],
  declarations: [AppSettingsComponent, AppSettingsDetailComponent, AppSettingsUpdateComponent, AppSettingsDeleteDialogComponent],
  entryComponents: [AppSettingsDeleteDialogComponent],
})
export class TfsBackendAppSettingsModule {}
