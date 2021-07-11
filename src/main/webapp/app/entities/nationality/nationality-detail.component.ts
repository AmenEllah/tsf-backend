import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INationality } from 'app/shared/model/nationality.model';

@Component({
  selector: 'jhi-nationality-detail',
  templateUrl: './nationality-detail.component.html',
})
export class NationalityDetailComponent implements OnInit {
  nationality: INationality | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nationality }) => (this.nationality = nationality));
  }

  previousState(): void {
    window.history.back();
  }
}
