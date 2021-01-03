import {Component, OnInit} from '@angular/core';
import {DocumentInputLang} from "../../model/lang";
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {InputExcelDto} from "../../model/model";
import Url from "../../model/url";
import {MessageService} from "../../service/message.service";
import {decode} from "punycode";

@Component({
  selector: 'document-input',
  templateUrl: './document-input.component.html',
  styleUrls: ['./document-input.component.css']
})
export class DocumentInputComponent implements OnInit {
  lang = new DocumentInputLang();
  fileToUpload: string = null;
  fileName: string = null;
  form: FormGroup;

  constructor(private fb: FormBuilder, private httpClient: HttpClient,private messageService:MessageService) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      type: [''],
      fileName: ['']
    });
  }

  handleFileInput(files: FileList) {
    const userFile = files.item(0);
    this.fileName = userFile.name;
    const reader = new FileReader();
    reader.readAsDataURL(userFile);
    reader.onload = () => {
      this.fileToUpload = reader.result as string;
      this.fileToUpload = this.fileToUpload.slice(this.fileToUpload.indexOf('base64') + 7,this.fileToUpload.length);
    };
  }

  sendFile() {
    const fileModel: InputExcelDto = new InputExcelDto();
    fileModel.content = this.fileToUpload;
    fileModel.fileName = this.fileName;
    this.httpClient.post(Url.EXCEL_UPLOAD, fileModel)
      .subscribe(value => this.messageService.showGeneralSuccess(this.lang.successSave));
  }
}
