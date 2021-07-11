import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IResidencyDocument, ResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from './residency-document.service';
import { ResidencyDocumentComponent } from './residency-document.component';
import { ResidencyDocumentDetailComponent } from './residency-document-detail.component';
import { ResidencyDocumentUpdateComponent } from './residency-document-update.component';

@Injectable({ providedIn: 'root' })
export class ResidencyDocumentResolve implements Resolve<IResidencyDocument> {
  constructor(private service: ResidencyDocumentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResidencyDocument> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((residencyDocument: HttpResponse<ResidencyDocument>) => {
          if (residencyDocument.body) {
            return of(residencyDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ResidencyDocument());
  }
}

export const residencyDocumentRoute: Routes = [
  {
    path: '',
    component: ResidencyDocumentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.residencyDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResidencyDocumentDetailComponent,
    resolve: {
      residencyDocument: ResidencyDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.residencyDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResidencyDocumentUpdateComponent,
    resolve: {
      residencyDocument: ResidencyDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.residencyDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResidencyDocumentUpdateComponent,
    resolve: {
      residencyDocument: ResidencyDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.residencyDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
