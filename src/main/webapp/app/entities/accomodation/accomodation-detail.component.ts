import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccomodation } from 'app/shared/model/accomodation.model';

@Component({
  selector: 'jhi-accomodation-detail',
  templateUrl: './accomodation-detail.component.html',
})
export class AccomodationDetailComponent implements OnInit {
  accomodation: IAccomodation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ accomodation }) => (this.accomodation = accomodation));
  }

  previousState(): void {
    window.history.back();
  }
}
