import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompte, Compte } from 'app/shared/model/compte.model';
import { CompteService } from './compte.service';
import { CompteComponent } from './compte.component';
import { CompteDetailComponent } from './compte-detail.component';
import { CompteUpdateComponent } from './compte-update.component';

@Injectable({ providedIn: 'root' })
export class CompteResolve implements Resolve<ICompte> {
  constructor(private service: CompteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompte> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((compte: HttpResponse<Compte>) => {
          if (compte.body) {
            return of(compte.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Compte());
  }
}

export const compteRoute: Routes = [
  {
    path: '',
    component: CompteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.compte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CompteDetailComponent,
    resolve: {
      compte: CompteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.compte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CompteUpdateComponent,
    resolve: {
      compte: CompteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.compte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CompteUpdateComponent,
    resolve: {
      compte: CompteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.compte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
