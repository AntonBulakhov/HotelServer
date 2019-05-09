import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ReviewModel} from "../../../../models/review-model";
import {ReviewService} from "../../../../services/review.service";

@Component({
  selector: 'app-reviewblank',
  templateUrl: './reviewblank.component.html',
  styleUrls: ['./reviewblank.component.css']
})
export class ReviewblankComponent implements OnInit {

  public review: ReviewModel = new ReviewModel();

  reviewForm: FormGroup;
  submitted = false;
  form: FormGroup;

  constructor(private reviewService: ReviewService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    // this.numberControl = new FormControl('', Validators.compose([Validators.required,Validators.min(9999999)])) ;
    // this.emailControl = new FormControl('', Validators.compose([
    //   Validators.required,
    //   Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
    // ]));
    // this.rewiewControl = new FormControl('', Validators.required);

    this.reviewForm = this.formBuilder.group({
      mark : ['', Validators.required],
      comment:['', [Validators.required]],
    });

    this.review.mark = 5;
  }

  get f() { return this.reviewForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.reviewForm.invalid) {
      return;
    }

    this.comment();

  }


  public comment():void{
    this.reviewService.comment(this.review).subscribe(()=>{
      setTimeout(location.reload.bind(location), 200);
    })
  }
}

