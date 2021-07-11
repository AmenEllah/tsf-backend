import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';

@Component({
  selector: 'jhi-authentitfication-to-sign-detail',
  templateUrl: './authentitfication-to-sign-detail.component.html',
})
export class AuthentitficationToSignDetailComponent implements OnInit {
  authentitficationToSign: IAuthentitficationToSign | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ authentitficationToSign }) => (this.authentitficationToSign = authentitficationToSign));
  }

  previousState(): void {
    window.history.back();
  }
}
