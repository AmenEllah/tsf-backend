import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFAQ } from 'app/shared/model/faq.model';

@Component({
  selector: 'jhi-faq-detail',
  templateUrl: './faq-detail.component.html',
})
export class FAQDetailComponent implements OnInit {
  fAQ: IFAQ | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fAQ }) => (this.fAQ = fAQ));
  }

  previousState(): void {
    window.history.back();
  }
}
