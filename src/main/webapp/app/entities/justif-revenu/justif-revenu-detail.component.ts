import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJustifRevenu } from 'app/shared/model/justif-revenu.model';

@Component({
  selector: 'jhi-justif-revenu-detail',
  templateUrl: './justif-revenu-detail.component.html',
})
export class JustifRevenuDetailComponent implements OnInit {
  justifRevenu: IJustifRevenu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ justifRevenu }) => (this.justifRevenu = justifRevenu));
  }

  previousState(): void {
    window.history.back();
  }
}
