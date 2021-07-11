import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'request',
        loadChildren: () => import('./request/request.module').then(m => m.TfsBackendRequestModule),
      },
      {
        path: 'faq',
        loadChildren: () => import('./faq/faq.module').then(m => m.TfsBackendFAQModule),
      },
      {
        path: 'offer',
        loadChildren: () => import('./offer/offer.module').then(m => m.TfsBackendOfferModule),
      },
      {
        path: 'activity',
        loadChildren: () => import('./activity/activity.module').then(m => m.TfsBackendActivityModule),
      },
      {
        path: 'adress-info',
        loadChildren: () => import('./adress-info/adress-info.module').then(m => m.TfsBackendAdressInfoModule),
      },
      {
        path: 'agency',
        loadChildren: () => import('./agency/agency.module').then(m => m.TfsBackendAgencyModule),
      },
      {
        path: 'bank-account',
        loadChildren: () => import('./bank-account/bank-account.module').then(m => m.TfsBackendBankAccountModule),
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.TfsBackendCategoryModule),
      },
      {
        path: 'detail-offer',
        loadChildren: () => import('./detail-offer/detail-offer.module').then(m => m.TfsBackendDetailOfferModule),
      },
      {
        path: 'financial-info',
        loadChildren: () => import('./financial-info/financial-info.module').then(m => m.TfsBackendFinancialInfoModule),
      },
      {
        path: 'governorate',
        loadChildren: () => import('./governorate/governorate.module').then(m => m.TfsBackendGovernorateModule),
      },
      {
        path: 'municipality',
        loadChildren: () => import('./municipality/municipality.module').then(m => m.TfsBackendMunicipalityModule),
      },
      {
        path: 'personal-info',
        loadChildren: () => import('./personal-info/personal-info.module').then(m => m.TfsBackendPersonalInfoModule),
      },
      {
        path: 'required-doc',
        loadChildren: () => import('./required-doc/required-doc.module').then(m => m.TfsBackendRequiredDocModule),
      },
      {
        path: 'required-doc-income',
        loadChildren: () => import('./required-doc-income/required-doc-income.module').then(m => m.TfsBackendRequiredDocIncomeModule),
      },
      {
        path: 'required-doc-residency',
        loadChildren: () =>
          import('./required-doc-residency/required-doc-residency.module').then(m => m.TfsBackendRequiredDocResidencyModule),
      },
      {
        path: 'monthly-net-income',
        loadChildren: () => import('./monthly-net-income/monthly-net-income.module').then(m => m.TfsBackendMonthlyNetIncomeModule),
      },
      {
        path: 'residency-document',
        loadChildren: () => import('./residency-document/residency-document.module').then(m => m.TfsBackendResidencyDocumentModule),
      },
      {
        path: 'justif-revenu',
        loadChildren: () => import('./justif-revenu/justif-revenu.module').then(m => m.TfsBackendJustifRevenuModule),
      },
      {
        path: 'other-residency-file',
        loadChildren: () => import('./other-residency-file/other-residency-file.module').then(m => m.TfsBackendOtherResidencyFileModule),
      },
      {
        path: 'other-revenu-file',
        loadChildren: () => import('./other-revenu-file/other-revenu-file.module').then(m => m.TfsBackendOtherRevenuFileModule),
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.TfsBackendDocumentModule),
      },
      {
        path: 'authentitfication-to-sign',
        loadChildren: () =>
          import('./authentitfication-to-sign/authentitfication-to-sign.module').then(m => m.TfsBackendAuthentitficationToSignModule),
      },
      {
        path: 'req-doc-upload',
        loadChildren: () => import('./req-doc-upload/req-doc-upload.module').then(m => m.TfsBackendReqDocUploadModule),
      },
      {
        path: 'derogation',
        loadChildren: () => import('./derogation/derogation.module').then(m => m.TfsBackendDerogationModule),
      },
      {
        path: 'active-staff',
        loadChildren: () => import('./active-staff/active-staff.module').then(m => m.TfsBackendActiveStaffModule),
      },
      {
        path: 'staff-permission',
        loadChildren: () => import('./staff-permission/staff-permission.module').then(m => m.TfsBackendStaffPermissionModule),
      },
      {
        path: 'adresse',
        loadChildren: () => import('./adresse/adresse.module').then(m => m.TfsBackendAdresseModule),
      },
      {
        path: 'compte',
        loadChildren: () => import('./compte/compte.module').then(m => m.TfsBackendCompteModule),
      },
      {
        path: 'attachment',
        loadChildren: () => import('./attachment/attachment.module').then(m => m.TfsBackendAttachmentModule),
      },
      {
        path: 'supply-matrix',
        loadChildren: () => import('./supply-matrix/supply-matrix.module').then(m => m.TfsBackendSupplyMatrixModule),
      },
      {
        path: 'app-settings',
        loadChildren: () => import('./app-settings/app-settings.module').then(m => m.TfsBackendAppSettingsModule),
      },
      {
        path: 'subcontracting-staff',
        loadChildren: () => import('./subcontracting-staff/subcontracting-staff.module').then(m => m.TfsBackendSubcontractingStaffModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TfsBackendEntityModule {}
