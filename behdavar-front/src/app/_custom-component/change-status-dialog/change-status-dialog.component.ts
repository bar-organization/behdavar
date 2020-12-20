import {Component, Inject} from '@angular/core';
import {CONTRACT_STATUS_TITLE, ContractStatus} from "../../model/enum/ContractStatus";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ChangeStatusDialogLang} from "../../model/lang";
import {EnumValueTitle} from "../../model/enum/EnumValueTitle";

@Component({
  selector: 'change-status-dialog',
  templateUrl: 'change-status-dialog.component.html',
  styleUrls: ['./change-status-dialog.component.css']
})
export class ChangeStatusDialogComponent {
  lang: ChangeStatusDialogLang = new ChangeStatusDialogLang();
  contractStatusList: EnumValueTitle<string>[] = CONTRACT_STATUS_TITLE;

  constructor(
    public dialogRef: MatDialogRef<ChangeStatusDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public selectedStatus: ContractStatus) {
  }


  onNoClick(): void {
    this.dialogRef.close();
  }
}

