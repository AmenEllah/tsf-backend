import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAuthentitficationToSign, AuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';
import { AuthentitficationToSignService } from './authentitfication-to-sign.service';
import { AuthentitficationToSignComponent } from './authentitfication-to-sign.component';
import { AuthentitficationToSignDetailComponent } from './authentitfication-to-sign-detail.component';
import { AuthentitficationToSignUpdateComponent } from './authentitfication-to-sign-update.component';

@Injectable({ providedIn: 'root' })
export class AuthentitficationToSignResolve implements Resolve<IAuthentitficationToSign> {
  constructor(private service: AuthentitficationToSignService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAuthentitficationToSign> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((authentitficationToSign: HttpResponse<AuthentitficationToSign>) => {
          if (authentitficationToSign.body) {
            return of(authentitficationToSign.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AuthentitficationToSign());
  }
}

export const authentitficationToSignRoute: Routes = [
  {
    path: '',
    component: AuthentitficationToSignComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.authentitficationToSign.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AuthentitficationToSignDetailComponent,
    resolve: {
      authentitficationToSign: AuthentitficationToSignResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.authentitficationToSign.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AuthentitficationToSignUpdateComponent,
    resolve: {
      authentitficationToSign: AuthentitficationToSignResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.authentitficationToSign.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AuthentitficationToSignUpdateComponent,
    resolve: {
      authentitficationToSign: AuthentitficationToSignResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.authentitficationToSign.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
