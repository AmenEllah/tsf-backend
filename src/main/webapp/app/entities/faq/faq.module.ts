import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { FAQComponent } from './faq.component';
import { FAQDetailComponent } from './faq-detail.component';
import { FAQUpdateComponent } from './faq-update.component';
import { FAQDeleteDialogComponent } from './faq-delete-dialog.component';
import { fAQRoute } from './faq.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(fAQRoute)],
  declarations: [FAQComponent, FAQDetailComponent, FAQUpdateComponent, FAQDeleteDialogComponent],
  entryComponents: [FAQDeleteDialogComponent],
})
export class TfsBackendFAQModule {}
