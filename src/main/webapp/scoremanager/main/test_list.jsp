<%-- 成績参照 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
 
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <section class="me-4">
 
            <!-- ① 画面タイトル -->
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>
 
            <!-- ================= 科目情報検索 ================= -->
            <form action="TestListSubjectExecute.action" method="get">
               <div class="row border mx-3 mb-3 py-2 align-items-center rounded">
               
                    <div class="col-12 mb-2">
                        <p class="fw-bold mb-1">科目情報</p>
                    </div>
 
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="entYear">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}"
                                    <c:if test="${year == entYear}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="classNum">
                            <option value="">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}"
                                    <c:if test="${num == classNum}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="subjectCd">
						    <option value="">--------</option>
						    <c:forEach var="sub" items="${subject_set}">
						        <option value="${sub.cd}"
						            <c:if test="${sub.cd == subjectCd}">selected</c:if>>
						            ${sub.name}
						        </option>
						    </c:forEach>
						</select>
                        
                    </div>
 
                    <div class="col-3 text-center">
                        <button class="btn btn-secondary">検索</button>
                    </div>
                </div>
            </form>
 
            <!-- ================= 学生情報検索 ================= -->
            <form action="TestListStudentExecute.action" method="get">
                <div class="row border mx-3 mb-3 py-3 align-items-end rounded">
 
                    <div class="col-12 mb-2">
                        <p class="fw-bold mb-1">学生情報</p>
                    </div>
 
                    <div class="col-6">
                        <label class="form-label">学生番号</label>
                        <input type="text" class="form-control"
                               name="studentNo"
                               placeholder="学生番号を入力してください"
                               value="${studentNo}">
                    </div>
 
                    <div class="col-6 text-start">
                        <button class="btn btn-secondary mt-4">検索</button>
                    </div>
                </div>
            </form>
 
            <!-- ⑭ 利用方法案内 -->
            <div class="mx-3 text-primary">
                科目情報を検索した後 学生番号を入力して成績検索ボタンをクリックしてください
            </div>
 
            <!-- ================= 検索結果（科目検索） ================= -->
            <c:if test="${searchType == 'subject'}">
 
                <c:choose>
                    <c:when test="${subjectList.size() > 0}">
                        <div class="mx-3 mt-4">
                            検索結果：${subjectList.size()}件
                        </div>
 
                        <table class="table table-hover mx-3 mt-2">
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>学生名</th>
                                <th>クラス</th>
                                <th class="text-center">点数</th>
                            </tr>
 
                            <c:forEach var="t" items="${subjectList}">
                                <tr>
                                    <td>${t.entYear}</td>
                                    <td>${t.studentNo}</td>
                                    <td>${t.studentName}</td>
                                    <td>${t.classNum}</td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${empty t.points}">
                                                ―
                                            </c:when>
                                            <c:otherwise>
                                                ${t.points}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
 
                    <c:otherwise>
                        <div class="mx-3 mt-4">
                            成績情報が存在しませんでした。
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
 
        </section>
    </c:param>
</c:import>