import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequiredDoc, RequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from './required-doc.service';
import { RequiredDocComponent } from './required-doc.component';
import { RequiredDocDetailComponent } from './required-doc-detail.component';
import { RequiredDocUpdateComponent } from './required-doc-update.component';

@Injectable({ providedIn: 'root' })
export class RequiredDocResolve implements Resolve<IRequiredDoc> {
  constructor(private service: RequiredDocService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequiredDoc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requiredDoc: HttpResponse<RequiredDoc>) => {
          if (requiredDoc.body) {
            return of(requiredDoc.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequiredDoc());
  }
}

export const requiredDocRoute: Routes = [
  {
    path: '',
    component: RequiredDocComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDoc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequiredDocDetailComponent,
    resolve: {
      requiredDoc: RequiredDocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDoc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequiredDocUpdateComponent,
    resolve: {
      requiredDoc: RequiredDocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDoc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequiredDocUpdateComponent,
    resolve: {
      requiredDoc: RequiredDocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDoc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
