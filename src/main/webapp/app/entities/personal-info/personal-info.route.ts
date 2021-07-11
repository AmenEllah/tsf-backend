import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonalInfo, PersonalInfo } from 'app/shared/model/personal-info.model';
import { PersonalInfoService } from './personal-info.service';
import { PersonalInfoComponent } from './personal-info.component';
import { PersonalInfoDetailComponent } from './personal-info-detail.component';
import { PersonalInfoUpdateComponent } from './personal-info-update.component';

@Injectable({ providedIn: 'root' })
export class PersonalInfoResolve implements Resolve<IPersonalInfo> {
  constructor(private service: PersonalInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonalInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personalInfo: HttpResponse<PersonalInfo>) => {
          if (personalInfo.body) {
            return of(personalInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonalInfo());
  }
}

export const personalInfoRoute: Routes = [
  {
    path: '',
    component: PersonalInfoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.personalInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonalInfoDetailComponent,
    resolve: {
      personalInfo: PersonalInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.personalInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonalInfoUpdateComponent,
    resolve: {
      personalInfo: PersonalInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.personalInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonalInfoUpdateComponent,
    resolve: {
      personalInfo: PersonalInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.personalInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
